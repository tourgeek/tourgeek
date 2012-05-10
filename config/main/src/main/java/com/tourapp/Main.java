/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp;

/**
 * This is a convenience class, so users don't have to remember the path to SApplet.
 */
public class Main extends org.jbundle.Main
{
	private static final long serialVersionUID = 1L;

	/**
     * Constructor.
     */
    public Main()
    {
        super();
    }
    /**
     * Start this program.
     * server=rmiserver
     * codebase=webserver
     */
    public static void main(String args[])
    {
        org.jbundle.Main.main(args);
    }

}
