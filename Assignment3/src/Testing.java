public class Testing
{
    public class Parent {
        protected int x;
        public Parent() { this.x = 4; }
        public void print() {
            System.out.println(x);
        }
    }
    
    public class Sub extends Parent {
        public Sub() {
            this.x = 10;
        }
        @Override
        public void print() { System.out.println(x); }
    }
    
    private void run() {
        Parent s = new Sub();
        s.print();
    }
    
    public static void main(String[] args) {
        Testing t = new Testing();
        t.run();
    }
}
