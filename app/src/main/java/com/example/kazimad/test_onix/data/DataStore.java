package com.example.kazimad.test_onix.data;

import com.annotatedsql.annotation.provider.Provider;
import com.annotatedsql.annotation.provider.URI;
import com.annotatedsql.annotation.sql.Autoincrement;
import com.annotatedsql.annotation.sql.Column;
import com.annotatedsql.annotation.sql.PrimaryKey;
import com.annotatedsql.annotation.sql.Schema;
import com.annotatedsql.annotation.sql.Table;

/**
 * Kazimad on 30.04.2015.
 */

@Schema(className = "OnixSchema", dbName = "database.db", dbVersion = 1)
@Provider(authority = DataStore.AUTHORITY, schemaClass = "OnixSchema", name = "OnixProvider")
public class DataStore {
    public static final String AUTHORITY = "com.example.kazimad.test_onix.data.provider";

    @SuppressWarnings("UnusedDeclaration")
    @Table(SaveUser.TABLE_NAME)
    public static class SaveUser {
        public static final String TABLE_NAME = "player_table";
        @URI
        public static final String CONTENT_URI = TABLE_NAME;
        @PrimaryKey
        @Autoincrement
        @Column(type = Column.Type.INTEGER)
        public static final String ID = "_id";
        @Column(type = Column.Type.TEXT)
        public static final String LOG_IN = "login";
        @Column(type = Column.Type.TEXT)
        public static final String PASS = "pass";
    }
}
