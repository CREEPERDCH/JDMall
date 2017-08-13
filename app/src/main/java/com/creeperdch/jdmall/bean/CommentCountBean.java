package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

public class CommentCountBean {
    private int allComment;
    private int positiveCom;
    private int moderateCom;
    private int negativeCom;
    private int hasImgCom;

    public CommentCountBean() {
    }

    public CommentCountBean(int allComment, int positiveCom, int moderateCom, int negativeCom, int hasImgCom) {
        this.allComment = allComment;
        this.positiveCom = positiveCom;
        this.moderateCom = moderateCom;
        this.negativeCom = negativeCom;
        this.hasImgCom = hasImgCom;
    }

    public int getAllComment() {
        return allComment;
    }

    public void setAllComment(int allComment) {
        this.allComment = allComment;
    }

    public int getPositiveCom() {
        return positiveCom;
    }

    public void setPositiveCom(int positiveCom) {
        this.positiveCom = positiveCom;
    }

    public int getModerateCom() {
        return moderateCom;
    }

    public void setModerateCom(int moderateCom) {
        this.moderateCom = moderateCom;
    }

    public int getNegativeCom() {
        return negativeCom;
    }

    public void setNegativeCom(int negativeCom) {
        this.negativeCom = negativeCom;
    }

    public int getHasImgCom() {
        return hasImgCom;
    }

    public void setHasImgCom(int hasImgCom) {
        this.hasImgCom = hasImgCom;
    }

    @Override
    public String toString() {
        return "CommentCountBean{" +
                "allComment=" + allComment +
                ", positiveCom=" + positiveCom +
                ", moderateCom=" + moderateCom +
                ", negativeCom=" + negativeCom +
                ", hasImgCom=" + hasImgCom +
                '}';
    }
}
