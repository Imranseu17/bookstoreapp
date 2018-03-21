package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;

@Entity
public class Book extends Model {
    @Id
    @Constraints.Required
    public  Integer id;
    @Constraints.Required
    @Constraints.MaxLength(100)
    @Constraints.MinLength(5)
    public  String title;
    @Constraints.Required
    @Constraints.Max(500)
    @Constraints.Min(5)
    public  Integer price;
    @Constraints.Required
    @Constraints.MaxLength(100)
    @Constraints.MinLength(5)
    public  String author;


    public static Finder<Integer,Book> finder = new Finder<>(Book.class);

//    public Book() {
//    }
//
//    public Book(Integer id, String title, Integer price, String author) {
//        this.id = id;
//        this.title = title;
//        this.price = price;
//        this.author = author;
//    }
//
//    private  static Set<Book> bookSet;
//
//    static {
//
//        bookSet = new HashSet<>();
//        bookSet.add(new Book(1,"C++",120,"ABC"));
//        bookSet.add(new Book(2,"Java",200,"XYZ"));
//
//    }
//
//    public static  Set<Book> allbooks(){
//        return  bookSet;
//    }
//
//    public static Book findByID(Integer id){
//        for (Book book:bookSet){
//            if(id.equals(book.id))
//                return book;
//        }
//
//        return null;
//    }
//
//    public  static void add(Book book){
//        bookSet.add(book);
//    }
//
//    public static boolean remove(Book book){
//        return bookSet.remove(book);
//    }
}
