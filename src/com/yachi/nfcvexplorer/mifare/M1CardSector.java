package com.yachi.nfcvexplorer.mifare;

import android.nfc.tech.MifareClassic;
/**
 * M1卡扇区对象
 * @author xiaoyl
 * @date 2013-07-15
 */
public class M1CardSector
{
  public int BLOCKCOUNT = 4;
  public boolean authorized;
  public M1CardBlock[] blocks = new M1CardBlock[this.BLOCKCOUNT];
  public byte[] keyA = {(byte)0xA0,(byte)0xA1,(byte)0xA2,(byte)0xA3,(byte)0xA4,(byte)0xA5};
  public byte[] keyB = {(byte)0xB0,(byte)0xB1,(byte)0xB2,(byte)0xB3,(byte)0xB4,(byte)0xB5};;

  public M1CardSector()
  {
       
  }
}
