package com.lambda.web.proxy;

import com.lambda.web.mappers.MovieMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Data
@Component
public class Pager {
    @Autowired MovieMapper movieMapper;
    private int rowCount, startRow, endRow,
                pageCount, pageSize, startPage, endPage, nowPage,
                blockCount, blockSize, preBlock, nextBlock, nowBlock;

    public boolean existPrev, existNext;
    private String search;

    public MovieMapper getMovieMapper() {
        return movieMapper;
    }

    public void setMovieMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getPreBlock() {
        return preBlock;
    }

    public void setPreBlock(int preBlock) {
        this.preBlock = preBlock;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

    public int getNowBlock() {
        return nowBlock;
    }

    public void setNowBlock(int nowBlock) {
        this.nowBlock = nowBlock;
    }

    public boolean isExistPrev() {
        return existPrev;
    }

    public void setExistPrev(boolean existPrev) {
        this.existPrev = existPrev;
    }

    public boolean isExistNext() {
        return existNext;
    }

    public void setExistNext(boolean existNext) {
        this.existNext = existNext;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void paging(){ //db에 있는 count를 가져온다
        // rowCount가 나누어 떨어지지 않을때
        rowCount = movieMapper.count();
        startRow = nowPage * pageSize;
        endRow = (nowPage != (pageCount - 1)) ? startRow = (pageSize -1) : rowCount -1;
        pageCount = (rowCount % pageSize != 0) ? rowCount / pageSize + 1 : rowCount / pageSize;
        startPage = nowBlock * blockSize;
        endPage = (nowBlock != (blockCount -1)) ? startPage + (blockSize - 1) : pageCount - 1;
        //pageSize = 5;
        //nowPage = 0;
        blockCount = (pageCount % blockSize != 0) ? pageCount / blockSize +1:pageCount / blockSize ;
        preBlock = startPage - blockSize;
        nextBlock = startPage + blockSize;
        // blockSize = 5;
        nowBlock = nowPage / blockSize;

    }
}
