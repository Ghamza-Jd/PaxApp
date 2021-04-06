package com.paxapp.pax;

import android.graphics.Bitmap;

import com.pax.dal.IPrinter;
import com.pax.dal.exceptions.PrinterDevException;

public class Printer {
    private static Printer printer;
    private final IPrinter iPrinter;

    private Printer() {
        iPrinter = NeptuneDevices.getDal().getPrinter();
    }

    public static Printer getInstance() {
        if (printer == null) {
            printer = new Printer();
        }
        return printer;
    }

    public void init() {
        try {
            iPrinter.init();
        } catch (PrinterDevException e) {
            e.printStackTrace();
        }
    }

    public void printStr(String str, String charset) {
        try {
            iPrinter.printStr(str, charset);
        } catch (PrinterDevException e) {
            e.printStackTrace();
        }

    }

    public void step(int b) {
        try {
            iPrinter.step(b);
        } catch (PrinterDevException e) {
            e.printStackTrace();
        }
    }

    public void printBitmap(Bitmap bitmap) {
        try {
            iPrinter.printBitmap(bitmap);
        } catch (PrinterDevException e) {
            e.printStackTrace();
        }
    }

    public String start() {
        try {
            int res = iPrinter.start();
            return statusCode2Str(res);
        } catch (PrinterDevException e) {
            e.printStackTrace();
            return "";
        }

    }


    public String statusCode2Str(int status) {
        String res = "";
        switch (status) {
            case 0:
                res = "Success ";
                break;
            case 1:
                res = "Printer is busy ";
                break;
            case 2:
                res = "Out of paper ";
                break;
            case 3:
                res = "The format of print data packet error ";
                break;
            case 4:
                res = "Printer malfunctions ";
                break;
            case 8:
                res = "Printer over heats ";
                break;
            case 9:
                res = "Printer voltage is too low";
                break;
            case 240:
                res = "Printing is unfinished ";
                break;
            case 252:
                res = " The iPrinter has not installed fonts library ";
                break;
            case 254:
                res = "Data package is too long ";
                break;
            default:
                break;
        }
        return res;
    }
}
