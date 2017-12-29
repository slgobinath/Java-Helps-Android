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

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Utility class for ExternalSQLiteOpenHelper.
 */
class Utility {
    private Utility() {

    }

    public static int readVersionFrom(String path) {
        int externalVersion;
        Scanner scanner = null;
        try {
            File versionFile = new File(path);
            scanner = new Scanner(versionFile, Charset.defaultCharset().name());
            if (scanner.hasNextInt()) {
                externalVersion = scanner.nextInt();
                if (externalVersion < 1) {
                    throw new IllegalArgumentException("Version must be >= 1, was " + externalVersion);
                }
            } else {
                throw new ExternalSQLiteOpenHelperException(path + " does not contain a valid integer version number");
            }
        } catch (FileNotFoundException e) {
            throw new ExternalSQLiteOpenHelperException(path + " file does not exist in source directory");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        System.out.println("version.info = " + externalVersion);
        return externalVersion;
    }

    public static String readUpgradeScriptFromAssets(Context context, String path) {
        String script = null;
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(path);
            script = readFromStream(inputStream);
        } catch (IOException e) {
            // Do nothing
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(ExternalSQLiteOpenHelperConstants.TAG, "Failed to close the script file");
                }
            }
        }

        return script;
    }

    public static String readUpgradeScriptFromExternalSource(String path) {
        String script = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            script = readFromStream(inputStream);
        } catch (FileNotFoundException e) {
            // Do nothing since the script is optional
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(ExternalSQLiteOpenHelperConstants.TAG, "Failed to close the script file");
                }
            }
        }

        return script;
    }

    public static boolean assetFileExists(Context context, String path) {
        InputStream inputStream = null;
        boolean exists = false;
        try {
            inputStream = context.getAssets().open(path);
            exists = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(ExternalSQLiteOpenHelperConstants.TAG, "Error in closing file " + path + " from assets");
                }
            }
        }

        return exists;
    }

    public static void copyDatabaseFromAssets(Context context, String path, File destination) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(path);
            copyStreamTo(inputStream, destination);
        } catch (IOException e) {
            throw new ExternalSQLiteOpenHelperException("Failed to open database from assets/" + path);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                Log.e(ExternalSQLiteOpenHelperConstants.TAG, "Failed to close the destination");
            }
        }
    }

    public static void copyDatabaseFromExternalSource(File source, File destination) {
        if (source.exists()) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(source);
                copyStreamTo(inputStream, destination);
            } catch (FileNotFoundException e) {
                throw new ExternalSQLiteOpenHelperException("Failed to open database from external source");
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    Log.e(ExternalSQLiteOpenHelperConstants.TAG, "Failed to close the destination");
                }
            }
        }
    }

    private static String readFromStream(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        String script = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            if (builder.length() > 0) {
                script = builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(ExternalSQLiteOpenHelperConstants.TAG, "Failed to close the script file");
                }
            }
        }

        return script;
    }

    private static void copyStreamTo(InputStream inputStream, File destination) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw new ExternalSQLiteOpenHelperException("Failed to open database destination");
        } catch (IOException e) {
            throw new ExternalSQLiteOpenHelperException("Failed to copy external database to the destination");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Log.e(ExternalSQLiteOpenHelperConstants.TAG, "Failed to close the external database");
            }
        }
    }

    /**
     * This method check for the necessary permissions. If any of them are not provided,
     * will throw an exception.
     *
     * @param context the Android context
     */
    public static void validatePermissions(Context context) {
        // Check for READ_EXTERNAL_STORAGE_PERMISSION
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(context, ExternalSQLiteOpenHelperConstants.READ_EXTERNAL_STORAGE_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            throw new ExternalSQLiteOpenHelperException(ExternalSQLiteOpenHelperConstants.READ_EXTERNAL_STORAGE_PERMISSION + " permission is not granted");
        }
    }

    public static String getUpgradeScriptPath(String parent, int oldVersion, int newVersion) {
        String path = parent + File.separator + String.format(ExternalSQLiteOpenHelperConstants.UPGRADE_SCRIPT, oldVersion, newVersion);
        return path;
    }
}
