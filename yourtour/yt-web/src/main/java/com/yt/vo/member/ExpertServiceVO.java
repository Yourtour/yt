package com.yt.vo.member;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.vo.BaseVO;

public class ExpertServiceVO extends BaseVO {
    private String title;
    private String free;
    private String feeIncluding;
    private String feeExcluding;
    private String withdraw;
    private String memo;


    public ExpertServiceVO() {
    }

    public static ExpertServiceBean transform(ExpertServiceVO vo){
        ExpertServiceBean bean = new ExpertServiceBean();
        bean.setGraphId(vo.getId());
        bean.setTitle(vo.getTitle());
        bean.setFree(vo.getFree());
        bean.setMemo(vo.getMemo());
        bean.setWithdraw(vo.getWithdraw());
        bean.setFeeExcluding(vo.getFeeExcluding());
        bean.setFeeIncluding(vo.getFeeIncluding());

        return bean;
    }

    public static ExpertServiceVO transform(ExpertServiceBean bean){
        ExpertServiceVO valueObject = new ExpertServiceVO();

        valueObject.setId(bean.getGraphId());
        valueObject.setFree(bean.getFree());
        valueObject.setTitle(bean.getTitle());
        valueObject.setMemo(bean.getMemo());
        valueObject.setWithdraw(bean.getWithdraw());
        valueObject.setFeeExcluding(bean.getFeeExcluding());
        valueObject.setFeeIncluding(bean.getFeeIncluding());

        return valueObject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFeeIncluding() {
        return feeIncluding;
    }

    public void setFeeIncluding(String feeIncluding) {
        this.feeIncluding = feeIncluding;
    }

    public String getFeeExcluding() {
        return feeExcluding;
    }

    public void setFeeExcluding(String feeExcluding) {
        this.feeExcluding = feeExcluding;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }
}
