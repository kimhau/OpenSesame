package com.example.juleeyahwright.opensesame.CreateReport;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateReportController {

    private CreateReportListener listener;
    private FirebaseFirestore db;

    public CreateReportController(@NonNull FirebaseFirestore db, CreateReportListener listener) {
        this.db = db;
        this.listener = listener;
    }

    void writeReport(@NonNull final Report report) {

        db.collection("reports")
                .add(report.getFirebaseMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        listener.reportCreateSuccess(report);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.reportCreateFailure(report, e);
                    }
                });
    }

}
