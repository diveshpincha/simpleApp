package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hof_table")
data class HighScores(
    @PrimaryKey(autoGenerate = true)
    var key:Long=0L,

    @ColumnInfo(name="player_name")
    var name:String="unKnown",

    @ColumnInfo(name="milli_time")
    var time: Long=0L,

    @ColumnInfo(name="player_time")
    var dis_time:String="",

    @ColumnInfo(name="moves_used")
    var moves:Int = 0
)
