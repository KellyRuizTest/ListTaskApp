package com.example.todolistappmine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context1;
    private List<Tasking> uploads;
    private RecyclerView.OnItemTouchListener listener;


    public interface OnItemClickListener {
        void onItemClick(Tasking aux);
    }

    public TaskAdapter(Context context, List<Tasking> tareitas, RecyclerView.OnItemTouchListener listener){

        context1 = context;
        uploads = tareitas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context1).inflate(R.layout.task_view, viewGroup, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder taskViewHolder, int i) {

        Tasking taskAux = uploads.get(i);
        taskViewHolder.textViewTask.setText(taskAux.getNombre());

        if ((taskAux.getPrioridad() == 1)){
            taskViewHolder.imageViewPrioridad.setImageResource(R.drawable.low);
        } else if (taskAux.getPrioridad() == 2){
            taskViewHolder.imageViewPrioridad.setImageResource(R.drawable.media);

        } else if (taskAux.getPrioridad() == 3){
            taskViewHolder.imageViewPrioridad.setImageResource(R.drawable.high);
        }


        SimpleDateFormat newFecha = new SimpleDateFormat("yyyy-MM-dd");

        String auxS = newFecha.format(new Date());
        taskViewHolder.textDate.setText(auxS);


    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTask;
        public ImageView imageViewPrioridad;
        public TextView textDate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTask = itemView.findViewById(R.id.name_task);
            imageViewPrioridad = itemView.findViewById(R.id.image_prioridad);
            textDate = itemView.findViewById(R.id.text_date);
        }

        public void bind(final Tasking aux, final OnItemClickListener listener){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(aux);

                    Intent intentDescription = new Intent(this,)
                }
            });

        }
    }

}
