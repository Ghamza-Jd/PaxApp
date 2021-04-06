package com.paxapp.pax;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.pax.dal.IPrinter;
import com.pax.gl.page.IPage;
import com.pax.gl.page.PaxGLPage;
import com.pax.neptunelite.api.NeptuneLiteUser;

public class PaxModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    PaxModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "PaxModule";
    }

    @ReactMethod
    public void print(){
        PaxGLPage paxGLPage = PaxGLPage.getInstance(reactContext);
        IPage page = paxGLPage.createPage();
        IPage.ILine.IUnit unit = page.createUnit();
        PageWrapper pageWrapper = new PageWrapper(page);
        pageWrapper.addLine().addUnit("Testing print");
        int width = 384;
        Bitmap bitmap = paxGLPage.pageToBitmap(page, width);
        unit.setBitmap(bitmap);
        try {
            IPrinter printer = NeptuneLiteUser.getInstance().getDal(reactContext).getPrinter();
            printer.init();
            Printer.getInstance().init();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            options.outHeight = 140;
            options.outWidth = 140;
            printer.printBitmap(bitmap);
            printer.printStr("\n\n------------------------------", null);
            printer.printStr("\n------------------------------\n\n", null);
            printer.step(Integer.parseInt(String.valueOf(120).trim()));
            printer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
