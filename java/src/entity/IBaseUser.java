package entity;

public interface IBaseUser {
    public abstract boolean isEnabled();
    public abstract int getId();
    public abstract int banUser();
}
