package com.example.precious_lord.agsec;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionsRecyclerAdapter extends RecyclerView.Adapter<QuestionsRecyclerAdapter.QuestionsViewHolder>{

    private ArrayList<Question> listQuestions;
    public ImageView overflow;
    private Context mContext;

    public QuestionsRecyclerAdapter(ArrayList<Question> listQuestions, Context mContext) {
        this.listQuestions = listQuestions;
        this.mContext = mContext;
    }

    public QuestionsRecyclerAdapter(){

    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView dbEmail;
        public TextView dbDomain;
        public TextView dbQuestion;
        public TextView dbReply;
        public ImageView imgViewEdit;
        public ImageView imgViewDelete;

        private final Context context;

        public QuestionsViewHolder(View itemView) {
            super(itemView);
            dbEmail = itemView.findViewById(R.id.dbQuestionEmail);
            dbDomain = itemView.findViewById(R.id.dbQuestionDomain);
            dbQuestion = itemView.findViewById(R.id.dbQuestionquestion);
            dbReply = itemView.findViewById(R.id.dbQuestionReply);

            imgViewEdit = itemView.findViewById(R.id.imgViewEdit);
            imgViewDelete = itemView.findViewById(R.id.imgViewDelete);

            itemView.setOnClickListener(this);
            imgViewEdit.setOnClickListener(this);
            imgViewDelete.setOnClickListener(this);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

            final Intent intent;

            intent = EditQuestion.makeIntent(context, dbEmail.getText().toString(), dbDomain.getText().toString(),
                    dbQuestion.getText().toString());

            context.startActivity(intent);

        }
    }


    @NonNull
    @Override
    public QuestionsRecyclerAdapter.QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.questionhistory_format, parent, false);
        return new QuestionsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull QuestionsRecyclerAdapter.QuestionsViewHolder holder, int position) {
        holder.dbEmail.setText(listQuestions.get(position).getUser_email());
        holder.dbDomain.setText(listQuestions.get(position).getQuestion_domain());
        holder.dbQuestion.setText(listQuestions.get(position).getQuestion_detail());
        holder.dbReply.setText(listQuestions.get(position).getQuestion_reply());
    }

    @Override
    public int getItemCount() {
        return listQuestions.size();
    }

    public void setFilter(ArrayList<Question> newList){
        listQuestions = new ArrayList<>();
        listQuestions.addAll(newList);
        notifyDataSetChanged();
    }
}
