package com.estiam.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.estiam.todo.room.Todo;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    //stock la liste des todos de l'adapter
    private List<Todo> todos;

    //represente la vue pour un item
    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvUrgency;

        public TodoViewHolder(View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvUrgency = itemView.findViewById(R.id.tvUrgency);
        }

    }

    public TodoAdapter(List<Todo> todos){
        this.todos = todos;
    }

    //crée la vue pour l'item
    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    //lier un todo avec la view
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.tvName.setText(todo.getName());
        holder.tvUrgency.setText(todo.getUrgency());
    }

    //nbr d'élements dans la liste de l'adapter
    @Override
    public int getItemCount()
    {
        return todos.size();
    }

}
