package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jaydave_sanchitshah_comp304_lab_assign4.R;
import com.example.jaydave_sanchitshah_comp304_lab_assign4.UpdateTest;

import java.util.List;

import models.Test;
import viewModel.TestviewModel;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    Context context;

    private List<Test> testList;

    public TestAdapter(Context context,List<Test> testList){
        this.context=context;
        this.testList=testList;
    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_test,parent,false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, final int position) {

        //Setting Textview data element from Test Models

        holder.tv_patient_id.setText(""+testList.get(position).getPatientId());
        holder.tv_nurse_id.setText(""+testList.get(position).getNurseId());
        holder.tv_temperature.setText(testList.get(position).getTemperature());
        holder.tv_bph.setText(testList.get(position).getBPH());
        holder.tv_bpl.setText(testList.get(position).getBPL());
        holder.tv_ph.setText(testList.get(position).getPH());
        holder.tv_test_id.setText(""+testList.get(position).getTestId());


        //Button OnClickListener to move to View Screen of Test
        holder.update_test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int patient_id=testList.get(position).getPatientId();
                int test_id=testList.get(position).getTestId();
                int nurse_id=testList.get(position).getNurseId();
                String temp=testList.get(position).getTemperature();
                String bph=testList.get(position).getBPH();
                String bpl=testList.get(position).getBPL();
                String ph=testList.get(position).getPH();

                Intent intent=new Intent(context, UpdateTest.class);

                intent.putExtra("patient_id",patient_id);
                intent.putExtra("test_id",test_id);
                intent.putExtra("nurse_id",nurse_id);
                intent.putExtra("temp",temp);
                intent.putExtra("bph",bph);
                intent.putExtra("bpl",bpl);
                intent.putExtra("ph",ph);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        TextView tv_patient_id,tv_nurse_id,tv_temperature,tv_bph,tv_bpl,tv_ph,tv_test_id;
        Button update_test_btn;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_patient_id=itemView.findViewById(R.id.tv_patient_id);
            tv_nurse_id=itemView.findViewById(R.id.tv_nurse_id);
            tv_temperature=itemView.findViewById(R.id.tv_temperature);
            tv_bph=itemView.findViewById(R.id.tv_bph);
            tv_bpl=itemView.findViewById(R.id.tv_bpl);
            update_test_btn=itemView.findViewById(R.id.update_test_btn);
            tv_ph=itemView.findViewById(R.id.tv_ph);
            tv_test_id=itemView.findViewById(R.id.tv_test_id);
        }
    }
}
