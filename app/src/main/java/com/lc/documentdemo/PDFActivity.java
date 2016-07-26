package com.lc.documentdemo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import butterknife.InjectView;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 7/26/16
 */
public class PDFActivity extends BaseActivity {

    private final int SELECT_PIC = 201;

    @InjectView(R.id.bt_select)
    Button bt_select;
    @InjectView(R.id.pdfView)
    PDFView pdfView;
    @InjectView(R.id.scrollBar)
    ScrollBar scrollBar;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void initView() {
        super.initView();
        pdfView.setScrollBar(scrollBar);
    }

    @Override
    protected void setListener() {
        super.setListener();
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent.ACTION_PICK
                //Intent.ACTION_VIEW
                //Intent.ACTION_CHOOSER
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                //MediaStore.Files.getContentUri("external")
//                intent.setDataAndType(MediaStore.Files., "application/pdf");
//                intent.setType("application/pdf");
//                startActivityForResult(intent, SELECT_PIC);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, SELECT_PIC);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       Logger.e("path=="+UriUtil.getPathFromUri(data,PDFActivity.this));
//        pdfView.fromUri(data.getData());
//        pdfView.loadPages();
        if (resultCode == RESULT_OK) {
            displayFromUri(data.getData());
        }
    }

    private void displayFromUri(Uri uri) {
//        String pdfFileName = getFileName(uri);
        Logger.e("filename");
        pdfView.fromUri(uri)
                .defaultPage(1)
                .onPageChange(onPageChangeListener)
                .swipeVertical(true)
                .showMinimap(false)
                .enableAnnotationRendering(true)
                .onLoad(onLoadCompleteListener)
                .load();
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }


    @Override
    protected void initData() {
        super.initData();
        pdfView.fromAsset("test.pdf")
                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true)
                .enableDoubletap(true)
                .swipeVertical(false)
                .defaultPage(1)
                .showMinimap(false)
                .onDraw(drawListener)
                .onLoad(onLoadCompleteListener)
                .onPageChange(onPageChangeListener)
                .onError(onErrorListener)
                .enableAnnotationRendering(false)
                .password(null)
                .showPageWithAnimation(true)
                .load();


    }

    private OnDrawListener drawListener = new OnDrawListener() {
        @Override
        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

        }
    };

    private OnLoadCompleteListener onLoadCompleteListener = new OnLoadCompleteListener() {
        @Override
        public void loadComplete(int nbPages) {

        }
    };

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageChanged(int page, int pageCount) {

        }
    };

    private OnErrorListener onErrorListener = new OnErrorListener() {
        @Override
        public void onError(Throwable t) {

        }
    };

}
