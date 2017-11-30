package damasterham.learningandroid.data.entitiy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Dude
{
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String nickName;

    public Dude(String name, String nickName)
    {
        this.name = name;
        this.nickName = nickName;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
}
