package com.michaelhangang.myapplication;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
       public Date data;
       public String  book;
       public int words;

       public Record(){}
       public Record(Date data, String book, int words){
              this.data =data;
              this.book =book;
              this.words =words;

       }


}
