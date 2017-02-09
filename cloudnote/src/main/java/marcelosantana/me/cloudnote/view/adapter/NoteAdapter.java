package marcelosantana.me.cloudnote.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import marcelosantana.me.cloudnote.R;
import marcelosantana.me.cloudnote.databinding.NoteItemBinding;
import marcelosantana.me.cloudnote.domain.Note;
import marcelosantana.me.cloudnote.viewModel.NoteViewModel;

/**
 * Created by Marcelo Santana on 25/03/2016.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.BindingHolder> {
    private Context mContext;
    private List<Note> mNotes;
    private boolean mIsUserNotes;

    public NoteAdapter(Context mContext, boolean isUserNotes) {
        this.mContext = mContext;
        this.mIsUserNotes = isUserNotes;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NoteItemBinding itemNoteBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.note_item,
                parent,
                false);
        return new BindingHolder(itemNoteBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        NoteItemBinding noteBinding = holder.mNoteBinding;
        noteBinding.setViewModel(new NoteViewModel(mContext, mNotes.get(position), mIsUserNotes));
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }


    public void addItem(Note note) {
        if (!mNotes.contains(note)) {
            mNotes.add(note);
            notifyItemInserted(mNotes.size() - 1);
        } else {
            mNotes.set(mNotes.indexOf(note), note);
            notifyItemChanged(mNotes.indexOf(note));
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private NoteItemBinding mNoteBinding;

        BindingHolder(NoteItemBinding itemView) {
            super(itemView.cardView);
            this.mNoteBinding = itemView;
        }
    }
}
