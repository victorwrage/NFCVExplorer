package com.yachi.nfcvexplorer.mifare;

/**
 * M1卡对象
 * @author xiaoyl
 * @date 2013-07-15
 */
public class M1Card
{
  private M1CardSector[] card_date = new M1CardSector[this.sectorCount];
  private String card_uid = "";
  public int sectorCount = 16;
  
  
  public M1Card(String card_uid){
	  this.card_uid = card_uid;
  }
  public M1CardSector[] getCard_date()
  {
    return this.card_date;
  }

  public String getCard_uid()
  {
    return this.card_uid;
  }

  public void setCard_date(M1CardSector[] paramArrayOfM1CardSector)
  {
    this.card_date = paramArrayOfM1CardSector;
  }

  public void setCard_uid(String paramString)
  {
    this.card_uid = paramString;
  }

  public void setSector(int paramInt, M1CardSector paramM1CardSector)
  {
  }
}

/* Location:           D:\AndroidDEX\apk2java\dex2jar-0.0.9.9\classes_dex2jar.jar
 * Qualified Name:     com.victor.nfcrelease.nfc.M1Kard
 * JD-Core Version:    0.5.4
 */