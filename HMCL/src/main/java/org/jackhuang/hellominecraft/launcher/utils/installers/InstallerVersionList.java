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
package org.jackhuang.hellominecraft.launcher.utils.installers;

import java.util.Comparator;
import java.util.List;
import org.jackhuang.hellominecraft.utils.functions.Consumer;

/**
 *
 * @author hyh
 */
public abstract class InstallerVersionList implements Consumer<String[]> {
    /**
     * Refresh installer versions list from the downloaded content.
     * @param versions Minecraft versions you need to refresh
     * @throws java.lang.Exception
     */
    public abstract void refreshList(String[] versions) throws Exception;
    public abstract String getName();
    public abstract List<InstallerVersion> getVersions(String mcVersion);
    
    public static class InstallerVersion implements Comparable<InstallerVersion> {
	public String selfVersion, mcVersion;
	public String installer, universal;
        public String changelog;

	public InstallerVersion(String selfVersion, String mcVersion) {
	    this.selfVersion = selfVersion;
	    this.mcVersion = mcVersion;
	}

        @Override
        public int compareTo(InstallerVersion o) {
            return selfVersion.compareTo(o.selfVersion);
        }
    }
    
    public static class InstallerVersionComparator implements Comparator<InstallerVersion> {
        public static final InstallerVersionComparator INSTANCE = new InstallerVersionComparator();
        @Override
        public int compare(InstallerVersion o1, InstallerVersion o2) {
            return o2.compareTo(o1);
        }
    }

    @Override
    public void accept(String[] v) {
        try {
            refreshList(v);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
