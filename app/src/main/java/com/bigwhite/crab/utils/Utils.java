package com.bigwhite.crab.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.WindowManager;

import com.bigwhite.crab.bean.info.MobileInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2017/5/7.
 */

public class Utils {
    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static MobileInfo getMobileInfo(Activity context) {
        WindowManager windowManager = context.getWindowManager();
        MobileInfo mobileInfo = new MobileInfo();
        mobileInfo.setmScreenWidth(windowManager.getDefaultDisplay().getWidth());
        mobileInfo.setmScreenHeight(windowManager.getDefaultDisplay().getHeight());
        return mobileInfo;
    }

    public static File getSaveImgPath() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath()
                + "/bwcrab/" + System.currentTimeMillis() + ".jpg"
        );
    }

    public static String getIntentPath(Context context, Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context,MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context,contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(context,uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        return imagePath;
    }

    private static String getImagePath(Context context,Uri uri, String selection) {
        String Path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return Path;
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Bitmap compressImageFromFile(String srcPath, int requestW) {
        if (requestW == 0) {
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
            return bitmap;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);
        int mSampleSize = 1;
        int w = options.outWidth;
        int h = options.outHeight;
        if (w <= 0 || h <= 0) {
            return null;
        }
        int requestH = requestW;
        while ((h / mSampleSize > requestH) || (w / mSampleSize > requestW)) {
            mSampleSize = mSampleSize << 1;
        }
        options.inJustDecodeBounds = false;
        options.inMutable = true;
        options.inSampleSize = mSampleSize;
        bitmap = BitmapFactory.decodeFile(srcPath, options);
        return bitmap;
    }

    public static boolean saveMyBitmap(File f, Bitmap mBitmap) throws IOException {
        boolean saveComplete = true;
        try {
            f.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();

//
//			matrix.postScale(scaleWidth, scaleHeight);
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, (int) width, (int) height, new Matrix(), true);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();

            mBitmap.recycle();
            System.gc();
        } catch (FileNotFoundException e) {
            saveComplete = false;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            saveComplete = false;
        }
        return saveComplete;
    }

    public static MobileInfo getFileInfo(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        MobileInfo mobileInfo = new MobileInfo();
        mobileInfo.setmScreenHeight(options.outWidth);
        mobileInfo.setmScreenHeight(options.outHeight);
        return mobileInfo;
    }
}
