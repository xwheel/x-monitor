package com.xwheel.xmonitor.commons.base;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author: leihang@live.cn
 * @date: 2015-07-27 17:17:40
 * @description: DAO层异常封装
 */
public class DaoException extends RuntimeException {

    private Throwable cause;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        this.cause = cause;
    }

    public DaoException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public String getMessage() {
        StringBuffer message = new StringBuffer(super.getMessage());
        if (this.cause != null) {
            message.append("; DAO Exception is ").append(this.cause);
        }
        return message.toString();
    }

    public Throwable getCause() {
        return null == this.cause ? null : this.cause;
    }

    public void printStackTrace(PrintStream printStream) {
        if (null == getCause()) {
            super.printStackTrace(printStream);
        } else {
            printStream.println(this);
            getCause().printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        if (null == getCause()) {
            super.printStackTrace(printWriter);
        } else {
            printWriter.println(this);
            getCause().printStackTrace(printWriter);
        }
    }
}
