/*
 * Copyright 2016 Java Helps
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.javahelps.externalsqliteimporter;

public class ExternalSQLiteOpenHelperConstants {
    static final String TAG = ExternalSQLiteOpenHelper.class.getSimpleName();

    /**
     * Permission required for ExternalSQLiteOpenHelper.
     */
    public static final String READ_EXTERNAL_STORAGE_PERMISSION = "android.permission.READ_EXTERNAL_STORAGE";

    /**
     * Name of the file which contains the external version.
     */
    public static final String VERSION_INFO = "version.info";

    /**
     * SQL upgrade script pattern.
     */
    public static final String UPGRADE_SCRIPT = "upgrade_from_%d_to_%d.sql";

    /**
     * Directory in assets which contains the database and optional SQL script.
     */
    public static final String ASSETS_DATABASE = "database";

    /**
     * The attached database name.
     */
    public static final String ATTACHED_EXTERNAL_DATABASE_NAME = "externalDB";
}
