/*
   D-Bus Java Viewer
   Copyright (c) 2006 Peter Cox

   This program is free software; you can redistribute it and/or modify it
   under the terms of either the GNU Lesser General Public License Version 2 or the
   Academic Free Licence Version 2.1.

   Full licence texts are included in the COPYING file with this program.
 */
package org.freedesktop.dbus.viewer;

import static org.freedesktop.GetText.t;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.Action;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
class SaveFileAction extends TabbedSaveAction implements ChangeListener {

    private class SelectedTabIterator implements Iterator<TextFile> {

        boolean iterated = false;

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return !iterated;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public TextFile next() {
            if (iterated) {
                throw new NoSuchElementException(t("Already iterated"));
            }
            iterated = true;
            return getTextFile(tabbedPane.getSelectedIndex());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    SaveFileAction(JTabbedPane tabbedPane) {
        super(tabbedPane);

        enableAndSetName();

        tabbedPane.addChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        enableAndSetName();
    }

    /**
     * Enable and set the name of the action based on the shown tab
     */
    void enableAndSetName() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        boolean enabled = selectedIndex > -1;
        putValue(Action.NAME, t("Save ") + getFileName(selectedIndex) + "...");
        setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<TextFile> iterator() {
        return new SelectedTabIterator();
    }
}
