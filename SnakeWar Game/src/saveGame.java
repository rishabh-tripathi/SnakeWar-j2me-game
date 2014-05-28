import javax.microedition.rms.*;

public class saveGame
{
	private RecordStore rs = null;
	static final String SG = "saved";
	public String gameData[];
	public int index;
	public saveGame()
	{
		gameData=new String[10];
	}
	public void openRecStore()
  {
    try
    {
      // The second parameter indicates that the record store
      // should be created if it does not exist
      rs = RecordStore.openRecordStore(SG, true );
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }    


 public void closeRecStore()
  {
    try
    {
      rs.closeRecordStore();
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }


 public void deleteRecStore()
  {
    if (RecordStore.listRecordStores() != null)
    {
      try
      {
        RecordStore.deleteRecordStore(SG);
      }
      catch (Exception e)
      {
      System.out.println(e.toString());
      }
    }      
  }

public void writeRecord(String str)
  {
    byte[] rec = str.getBytes();

    try
    {
      rs.addRecord(rec, 0, rec.length);
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }

 public void readRecords()
  {		String str;
    try
    {
      byte[] recData = new byte[5]; 
      int len;
		index=rs.getNumRecords();
		
      for (int i = 1; i <= rs.getNumRecords(); i++)      
      {
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];
       
        len = rs.getRecord(i, recData, 0);
        
        str=new String(recData, 0, len);
		gameData[i-1]=str;
      }
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }

  }


}