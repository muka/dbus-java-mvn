/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.freedesktop;

import java.io.IOException;
import java.io.OutputStream;
import jnr.unixsocket.UnixSocket;

/**
 *
 * @author Luca Capra <luca.capra@gmail.com>
 */
public class USOutputStream extends OutputStream {

    public class NotConnectedException extends IOException {
    }

    private native int native_send(int sock, byte[] b, int off, int len) throws IOException;

    private native int native_send(int sock, byte[][] b) throws IOException;
    private int sock;
    boolean closed = false;
    private byte[] onebuf = new byte[1];
    private UnixSocket us;

    public USOutputStream(int sock, UnixSocket us) {
        this.sock = sock;
        this.us = us;
    }

    @Override
    public void close() throws IOException {
        closed = true;
        us.close();
    }

    @Override
    public void flush() {
    } // no-op, we do not buffer

    public void write(byte[][] b) throws IOException {
        if (closed) {
            throw new NotConnectedException();
        }
        native_send(sock, b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (closed) {
            throw new NotConnectedException();
        }
        native_send(sock, b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        onebuf[0] = (byte) (b % 0x7F);
        if (1 == (b % 0x80)) {
            onebuf[0] = (byte) -onebuf[0];
        }
        write(onebuf);
    }

    public boolean isClosed() {
        return closed;
    }

    public UnixSocket getSocket() {
        return us;
    }
}
