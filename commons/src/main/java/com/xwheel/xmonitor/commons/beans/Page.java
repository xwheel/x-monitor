package com.xwheel.xmonitor.commons.beans;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 通用的分页对象
 */

public class Page<T> implements Serializable {

    //------------------公共变量----------------//
    public static final String ASC = "asc";

    public static final String DESC = "desc";


    //------------------查询输入参数----------------//
    //排序字段
    protected String orderBy;

    //排序方向
    protected String order;

    //----- 分页参数 ----//
    //页码
    protected int pageNo = 1;
    //每页记录数  默认为-1(不分页)
    protected int pageSize = -1;
    //手动分页 开始记录数
    private int startPageNo;
    //手动分页 结束记录数
    private int endPageNO;


    //-----------以下是查询后需要封装的返回参数-----------//

    //总记录数
    private long totalCount;

    //总页数
    private long totalPages;

    //起始页
    private long startIndex;

    //结束页
    private long lastIndex;

    //返回当前页结果
    private List<T> currentPageResult = new ArrayList<T>();


    //默认构造函数
    public Page() {
    }

    //分页参数访问函数
    public Page(int pageNo, int pageSize) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final int pageNo) {
        this.pageNo = 1 > pageNo ? 1 : pageNo;
    }

    /**
     * 返回Page对象自身的setPageNo函数,可用于连续设置。
     */
    public Page<T> pageNo(final int thePageNo) {
        setPageNo(thePageNo);
        return this;
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
     */
    public int getStartIndex() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页最后一条记录在总结果集中的位置,序号从pageSize开始.
     */
    public int getLastIndex() {
        return pageNo * pageSize;
    }

    public int getEndPageNO() {
        return endPageNO;
    }

    public void setEndPageNO(int endPageNO) {
        this.endPageNO = endPageNO;
    }

    public int getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(int startPageNo) {
        this.startPageNo = startPageNo;
    }

    /**
     * 获得排序字段,无默认值. 多个排序字段时用','分隔.
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置排序字段,多个排序字段时用','分隔.
     */
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 设置排序方向.
     *
     * @param order 可选值为desc或asc,多个排序字段时用','分隔.
     */
    public void setOrder(final String order) {
        if (order != null) {
            String lowcaseOrder = StringUtils.lowerCase(order);
            //检查order字符串的合法值
            String[] orders = StringUtils.split(lowcaseOrder, ',');
            for (String orderStr : orders) {
                if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
                    throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
                }
            }
            this.order = lowcaseOrder;
        }
    }

    /**
     * 获得order值
     *
     * @return
     */
    public String getOrder() {
        return this.order;
    }


    //-- 访问查询结果函数 --//

    /**
     * 获得当前页内的记录列表.
     */
    public List<T> getCurrentPageResult() {
        return currentPageResult;
    }

    /**
     * 设置当前页内的记录列表.
     */
    public void setCurrentPageResult(final List<T> currentPageResult) {
        this.currentPageResult = currentPageResult;
    }

    /**
     * 获得总记录数, 默认值为1.
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 总记录数决定总页数,所以设置总页数的方法也写在这个方法内
     * 设置总记录数,总页数,以及判断当前页是否大于最大页
     */
    public void setTotalCount(final long totalCount) {
        //设置总记录数
        this.totalCount = totalCount;
        //设置总页数
        if (totalCount > 0) {
            long count = totalCount / pageSize;
            if (totalCount % pageSize > 0) {
                totalPages = count + 1;
            } else {
                totalPages = count;
            }
        }
        //判断当前页是否超过最大页,如果大于则当前页等于最大页
        if (totalPages < pageNo) {
            pageNo = (int) totalPages;
        }
    }

    /**
     * 获得总页数
     */

    public long getTotalPages() {
        return totalPages;
    }

    public void setLastIndex(long lastIndex) {
        this.lastIndex = lastIndex;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", order='" + order + '\'' +
                ", totalCount=" + totalCount +
                ", currentPageResult=" + currentPageResult +
                ", totalPages=" + totalPages +
                '}';
    }

}
