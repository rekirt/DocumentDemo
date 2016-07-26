package com.lc.documentdemo;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 7/26/16
 */
public class UriUtil {

    public static String getPathFromUri(Intent intent, Context context) {
        String type = intent.getType();
        Uri uri = intent.getData();
        Logger.e("==="+intent.getType());
        Logger.e("==="+intent.getScheme());
        Logger.e("==="+intent.getExtras());
        Logger.e("==="+intent.getData().getPath());
        Logger.e("==="+intent.getData().getEncodedFragment());
        String path = "";
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            path = uri.getEncodedPath();
            Logger.e("path1 is " + path);
            if (path != null) {
                path = Uri.decode(path);
                Logger.e("path2 is " + path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(")
                        .append(MediaStore.Images.ImageColumns.DATA)
                        .append("=")
                        .append("'" + path + "'")
                        .append(")");
                Cursor cur = cr.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    //do nothing
                } else {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/"+ index);
                    Logger.e("uri_temp is " + uri_temp);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return path;
    }
}
