package edu.wcu.jstrong1.library;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Class for library's recyclerView
 */
public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.MyViewHolder> {

    /** An array of BookModels that hold data about books **/
    private List<BookModel> mDataset;

    /** Observer for telling when an item is clicked */
    private ItemWasClicked observer;

//==============================================================================================
    /**
     * Provide a suitable constructor (depends on the kind of dataset)
     * @param myDataset List of BookModels
     */
    public LibraryListAdapter(List<BookModel> myDataset) {
        mDataset = myDataset;
    }//end

//==============================================================================================
    /**
     * Return the size of the dataset (invoked by the layout manager)
     * @return The data set size.
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }//end

//==============================================================================================
    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param holder The holder view which will hold one list item
     * @param position  The index position within the data set.
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        CoverHelper cv = new CoverHelper();
        Bitmap bookCover = cv.getImageBitmap(mDataset.get(position).getSmallCover());
        if (bookCover != null) {
            holder.bookCover.setImageBitmap(bookCover);
        } else {
            holder.bookCover.setImageResource(R.drawable.missing_book_cover);
//            holder.bookCover.setImageBitmap(BitmapFactory.decodeResource(Resources.getSystem(),
//                    R.drawable.no_book_cover));
        }
        holder.bookTitle.setText(mDataset.get(position).getTitle());
        holder.bookAuthor.setText(mDataset.get(position).getAuthor());
    }//end

    //==============================================================================================

    /**
     * Set the onItemClickedObserver
     * @param iwc new ItemWasClicked observer
     */
    public void setOnItemClickedObserver(ItemWasClicked iwc) {
        this.observer = iwc;
    }

    public interface ItemWasClicked{
        // Only a BookModel because the array is of type BookModels
        public void itemWasClicked(BookModel book);
    }
    //==============================================================================================
    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }
//==============================================================================================
    /**
     * Create new views (invoked by the layout manager)
     * @param parent The parent for this view is part of
     * @param viewType an int representing the viewType
     * @return An instance of the inner class MyViewHolder
     */
    @Override
    public LibraryListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view in this case a simple text view
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.library_list_item, parent,
                false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }//end
    //-------------------------------------------------------------------------------------------
    /**
     * An inner class to provide a reference to the views for each data item.
     * Complex data items may need more than one view per item, and you provide access to all the views for a data item in a view holder
     **/
    //-------------------------------------------------------------------------------------------
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /** ImageView for displaying a books cover **/
        public ImageView bookCover;

        /** ImageView for displaying a books title **/
        public TextView bookTitle;

        /** ImageView for displaying a books author **/
        public TextView bookAuthor;

        /**
         * A simple constructor with one view.
         * @param v A textView object to hold a string.
         */
        public MyViewHolder(ViewGroup v) {
            super(v);
            bookCover = v.findViewById(R.id.list_book_cover);
            bookTitle = v.findViewById(R.id.list_book_title);
            bookAuthor = v.findViewById(R.id.list_book_author);
            bookCover.setTag("cover");
            bookTitle.setTag("title");
            bookAuthor.setTag("author");

            v.setOnClickListener(this);
        }//End method

//        public void setOnItemCLickListener(View.OnClickListener v) {
//            name.setOnClickListener(v);
//            letter.setOnClickListener(v);
//        }

        @Override
        public void onClick(View view) {
            if (LibraryListAdapter.this.observer != null) {
                TextView tv = (TextView) bookTitle;
                Log.v("strong", "Layout position = " + this.getLayoutPosition());
                observer.itemWasClicked(mDataset.get(this.getLayoutPosition()));
            }
        }
    }//end class---------------------------------------------------------------------------------

}
