package damasterham.learningandroid.rest.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public abstract class BaseEntity
{
    @PrimaryKey
    private long id;

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }


}
