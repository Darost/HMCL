/*
 * Copyright 2013 huangyuhui <huanghongxun2008@126.com>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.
 */
package org.jackhuang.hellominecraft.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hyh
 */
public class JavaProcess {

    private final List<String> commands;
    private final Process process;
    private final ArrayList<String> stdOutLines = new ArrayList<>();
    private final ArrayList<String> stdErrLines = new ArrayList<>();

    public JavaProcess(List<String> commands, Process process, ProcessManager pm) {
        this.commands = commands;
        this.process = process;
        if(pm != null) pm.registerProcess(this);
    }

    public JavaProcess(String[] commands, Process process, ProcessManager pm) {
        this(Arrays.asList(commands), process, pm);
    }

    public Process getRawProcess() {
        return this.process;
    }

    public List<String> getStartupCommands() {
        return this.commands;
    }

    public String getStartupCommand() {
        return this.process.toString();
    }

    public ArrayList<String> getStdOutLines() {
        return this.stdOutLines;
    }

    public ArrayList<String> getStdErrLines() {
        return this.stdErrLines;
    }

    public boolean isRunning() {
        try {
            this.process.exitValue();
        } catch (IllegalThreadStateException ex) {
            return true;
        }

        return false;
    }

    public int getExitCode() {
        try {
            return this.process.exitValue();
        } catch (IllegalThreadStateException ex) {
            ex.fillInStackTrace();
            throw ex;
        }
    }

    @Override
    public String toString() {
        return "JavaProcess[commands=" + this.commands + ", isRunning=" + isRunning() + "]";
    }

    public void stop() {
        this.process.destroy();
    }
}