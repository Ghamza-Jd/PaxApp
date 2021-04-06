package com.paxapp.pax;

import com.pax.gl.page.IPage;

public class PageWrapper {
    IPage page;
    IPage.ILine line;

    public PageWrapper(IPage page) {
        this.page = page;
        addLine();
    }

    public PageWrapper addLine() {
        this.line = this.page.addLine();
        return this;
    }

    public PageWrapper addUnit(String text) {
        line.addUnit(page.createUnit().setText(text).setAlign(IPage.EAlign.LEFT).setFontSize(20));
        return this;
    }
}
