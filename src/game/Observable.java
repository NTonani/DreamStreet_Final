package game;


public interface Observable {
    public void registerObserver(Observer obs);
    public void unregisterObserver(Observer obs);
    public int notifyObservers(int xoffset, int yoffset);
    public void clearObservers();
}
