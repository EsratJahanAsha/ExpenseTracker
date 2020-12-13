package com.asha.expensetracker.controller;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class MyDatabase_Impl extends MyDatabase {
  private volatile MyDao _myDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `budget_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Amount` INTEGER NOT NULL, `AmountType` TEXT, `ExpenseType` TEXT, `WrittenNote` TEXT, `NoteDate` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"0020489ea43ab909674f09528a9f875e\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `budget_table`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsBudgetTable = new HashMap<String, TableInfo.Column>(6);
        _columnsBudgetTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsBudgetTable.put("Amount", new TableInfo.Column("Amount", "INTEGER", true, 0));
        _columnsBudgetTable.put("AmountType", new TableInfo.Column("AmountType", "TEXT", false, 0));
        _columnsBudgetTable.put("ExpenseType", new TableInfo.Column("ExpenseType", "TEXT", false, 0));
        _columnsBudgetTable.put("WrittenNote", new TableInfo.Column("WrittenNote", "TEXT", false, 0));
        _columnsBudgetTable.put("NoteDate", new TableInfo.Column("NoteDate", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBudgetTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBudgetTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBudgetTable = new TableInfo("budget_table", _columnsBudgetTable, _foreignKeysBudgetTable, _indicesBudgetTable);
        final TableInfo _existingBudgetTable = TableInfo.read(_db, "budget_table");
        if (! _infoBudgetTable.equals(_existingBudgetTable)) {
          throw new IllegalStateException("Migration didn't properly handle budget_table(com.asha.expensetracker.model.MyEntity).\n"
                  + " Expected:\n" + _infoBudgetTable + "\n"
                  + " Found:\n" + _existingBudgetTable);
        }
      }
    }, "0020489ea43ab909674f09528a9f875e", "3d0880b78be0697be27467c4ec854068");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "budget_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `budget_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public MyDao myDao() {
    if (_myDao != null) {
      return _myDao;
    } else {
      synchronized(this) {
        if(_myDao == null) {
          _myDao = new MyDao_Impl(this);
        }
        return _myDao;
      }
    }
  }
}
