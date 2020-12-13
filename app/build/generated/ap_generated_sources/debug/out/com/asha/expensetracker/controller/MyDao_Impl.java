package com.asha.expensetracker.controller;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.asha.expensetracker.model.MyEntity;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class MyDao_Impl implements MyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMyEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfMyEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfMyEntity;

  public MyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMyEntity = new EntityInsertionAdapter<MyEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `budget_table`(`id`,`Amount`,`AmountType`,`ExpenseType`,`WrittenNote`,`NoteDate`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getAmount());
        if (value.getAmount_type() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAmount_type());
        }
        if (value.getExpense_type() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExpense_type());
        }
        if (value.getWritten_note() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getWritten_note());
        }
        if (value.getDate_note() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate_note());
        }
      }
    };
    this.__deletionAdapterOfMyEntity = new EntityDeletionOrUpdateAdapter<MyEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `budget_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfMyEntity = new EntityDeletionOrUpdateAdapter<MyEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `budget_table` SET `id` = ?,`Amount` = ?,`AmountType` = ?,`ExpenseType` = ?,`WrittenNote` = ?,`NoteDate` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getAmount());
        if (value.getAmount_type() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAmount_type());
        }
        if (value.getExpense_type() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExpense_type());
        }
        if (value.getWritten_note() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getWritten_note());
        }
        if (value.getDate_note() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate_note());
        }
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  public long addBudget(MyEntity budget) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMyEntity.insertAndReturnId(budget);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteBudget(MyEntity budget) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfMyEntity.handle(budget);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateBudget(MyEntity budget) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfMyEntity.handle(budget);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<MyEntity> getAllBudgetRows() {
    final String _sql = "SELECT id,Amount,AmountType,ExpenseType,NoteDate,WrittenNote FROM budget_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("Amount");
      final int _cursorIndexOfAmountType = _cursor.getColumnIndexOrThrow("AmountType");
      final int _cursorIndexOfExpenseType = _cursor.getColumnIndexOrThrow("ExpenseType");
      final int _cursorIndexOfDateNote = _cursor.getColumnIndexOrThrow("NoteDate");
      final int _cursorIndexOfWrittenNote = _cursor.getColumnIndexOrThrow("WrittenNote");
      final List<MyEntity> _result = new ArrayList<MyEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MyEntity _item;
        _item = new MyEntity();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpAmount;
        _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        final String _tmpAmount_type;
        _tmpAmount_type = _cursor.getString(_cursorIndexOfAmountType);
        _item.setAmount_type(_tmpAmount_type);
        final String _tmpExpense_type;
        _tmpExpense_type = _cursor.getString(_cursorIndexOfExpenseType);
        _item.setExpense_type(_tmpExpense_type);
        final String _tmpDate_note;
        _tmpDate_note = _cursor.getString(_cursorIndexOfDateNote);
        _item.setDate_note(_tmpDate_note);
        final String _tmpWritten_note;
        _tmpWritten_note = _cursor.getString(_cursorIndexOfWrittenNote);
        _item.setWritten_note(_tmpWritten_note);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public MyEntity getBudgetRow(int id) {
    final String _sql = "SELECT id,Amount,AmountType,ExpenseType,WrittenNote,NoteDate FROM budget_table WHERE id IN (?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("Amount");
      final int _cursorIndexOfAmountType = _cursor.getColumnIndexOrThrow("AmountType");
      final int _cursorIndexOfExpenseType = _cursor.getColumnIndexOrThrow("ExpenseType");
      final int _cursorIndexOfWrittenNote = _cursor.getColumnIndexOrThrow("WrittenNote");
      final int _cursorIndexOfDateNote = _cursor.getColumnIndexOrThrow("NoteDate");
      final MyEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new MyEntity();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpAmount;
        _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
        _result.setAmount(_tmpAmount);
        final String _tmpAmount_type;
        _tmpAmount_type = _cursor.getString(_cursorIndexOfAmountType);
        _result.setAmount_type(_tmpAmount_type);
        final String _tmpExpense_type;
        _tmpExpense_type = _cursor.getString(_cursorIndexOfExpenseType);
        _result.setExpense_type(_tmpExpense_type);
        final String _tmpWritten_note;
        _tmpWritten_note = _cursor.getString(_cursorIndexOfWrittenNote);
        _result.setWritten_note(_tmpWritten_note);
        final String _tmpDate_note;
        _tmpDate_note = _cursor.getString(_cursorIndexOfDateNote);
        _result.setDate_note(_tmpDate_note);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getSumExpenditure(String date) {
    final String _sql = "SELECT SUM(Amount) FROM budget_table WHERE AmountType ='Expense' AND NoteDate LIKE(?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getSumIncome(String date) {
    final String _sql = "SELECT SUM(Amount) FROM budget_table WHERE AmountType ='Income' AND NoteDate LIKE(?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAvgExpenditure(String date) {
    final String _sql = "SELECT AVG(Amount) FROM budget_table WHERE AmountType ='Expense'AND NoteDate LIKE(?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAvgIncome(String date) {
    final String _sql = "SELECT AVG(Amount) FROM budget_table WHERE AmountType ='Income'AND NoteDate LIKE(?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getIncomeSum_all() {
    final String _sql = "SELECT SUM(Amount) FROM budget_table WHERE AmountType ='Income'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getIncomeAvg_all() {
    final String _sql = "SELECT AVG(Amount) FROM budget_table WHERE AmountType ='Income'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getExpenseSum_all() {
    final String _sql = "SELECT SUM(Amount) FROM budget_table WHERE AmountType ='Expense'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getExpenseAvg_all() {
    final String _sql = "SELECT AVG(Amount) FROM budget_table WHERE AmountType ='Expense'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getIsExist(String date) {
    final String _sql = "SELECT id FROM budget_table WHERE (AmountType IN('Income','Expense')) AND NoteDate LIKE(?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
