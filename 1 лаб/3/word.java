public class word {
    String name;
    int count = 1;
    public word(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "word{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
