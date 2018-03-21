package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.books.create;
import views.html.books.edit;
import views.html.books.index;
import views.html.books.show;
import views.html.errors._404;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class BooksController extends Controller{

    @Inject
    FormFactory formFactory;


    //for all books

    public Result index(){
        List<Book> books = Book.finder.all();
        return ok(index.render(books));
    }

    //to create book

    public Result create(){
        Form<Book> bookForm = formFactory.form(Book.class);

        return ok(create.render(bookForm));
    }

    //to save book

    public Result save(){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        if(bookForm.hasErrors()){
            flash("danger","please Correct the Form Below");
            return badRequest(create.render(bookForm));
        }
        Book book = bookForm.get();
        book.save();
        flash("success","Book save Successfully");
        return redirect(routes.BooksController.index());
    }

    // to edit book


    public Result edit(Integer id){
        Book book = Book.finder.byId(id);
        if(book == null){
            return notFound(_404.render());
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        return ok(edit.render(bookForm));
    }

    // to update book

    public Result update(){
       Form<Book>  bookForm = formFactory.form(Book.class).bindFromRequest();
        if(bookForm.hasErrors()){
            flash("danger","please Correct the Form Below");
            return badRequest(create.render(bookForm));
        }
        Book book = bookForm.get();
        Book oldbook = Book.finder.byId(book.id);

        if(oldbook == null){
            return notFound(_404.render());
        }

        oldbook.title = book.title;
        oldbook.author = book.author;
        oldbook.price = book.price;

        oldbook.update();
        flash("success","Book Update Successfully");
        return ok();
    }

    //to delete book

    public Result destroy(Integer id){
        Book book = Book.finder.byId(id);
        if(book == null){
            return notFound(_404.render());
        }


        book.delete();
        flash("success","Book delete Successfully");
        return ok();
    }

    //to show one book

    public Result show(Integer id){
        Book book  = Book.finder.byId(id);
        if(book == null)
            return notFound(_404.render());
        return ok(show.render(book));
    }
}
