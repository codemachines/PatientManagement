package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jaydave_sanchitshah_comp304_lab_assign4.R;
import com.example.jaydave_sanchitshah_comp304_lab_assign4.UpdatePatient;

import java.util.ArrayList;
import java.util.List;

import models.Patient;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {


    Context context;

    private List<Patient> patientList = new ArrayList<>();

    public PatientAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_patient,parent,false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, final int position) {

        holder.tv_patient_name.setText(patientList.get(position).getName());
        holder.tv_patient_id.setText("" + patientList.get(position).getPatientId());
        holder.tv_nurse_id.setText("" + patientList.get(position).getNurseId());
        holder.tv_room.setText(patientList.get(position).getRoom());

        holder.patient_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int patient_id=patientList.get(position).getPatientId();
                int nurse_id=patientList.get(position).getNurseId();
                String patient_name=patientList.get(position).getName();
                String patient_fname=patientList.get(position).getFirstname();
                String patient_lname=patientList.get(position).getLastname();

                Intent intent=new Intent(context, UpdatePatient.class);
                intent.putExtra("patient_id",patient_id);
                intent.putExtra("nurse_id",nurse_id);
                intent.putExtra("patient_name",patient_name);
                intent.putExtra("patient_fname",patient_fname);
                intent.putExtra("patient_lname",patient_lname);

                //FragmentTransaction fragmentTransaction=
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView tv_patient_name,tv_patient_id,tv_nurse_id,tv_room;
        Button patient_update_btn;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_patient_name=itemView.findViewById(R.id.tv_patient_name);
            tv_patient_id=itemView.findViewById(R.id.tv_patient_id);
            tv_nurse_id=itemView.findViewById(R.id.tv_nurse_id);
            tv_room=itemView.findViewById(R.id.tv_room);
            patient_update_btn=itemView.findViewById(R.id.patient_update_btn);
        }
    }
}
