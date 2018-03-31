package com.bigwhite.crab.utils;

import android.app.Activity;
import android.os.Environment;
import android.view.WindowManager;

import com.bigwhite.crab.bean.info.MobileInfo;

import java.io.File;
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
        + "/bwcrab/"  + System.currentTimeMillis() + ".jpg"
        );
    }
}
