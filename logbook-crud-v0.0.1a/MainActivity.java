package ***.***.***.logbookcrud;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final String TAG = "> logbook: ";
    private final String COLLECTION_NAME = "records";

    private final String INVOICE = "f01_invoice";
    private final String DATE = "f02_date";

    private final String DEP_TIME = "f05_depart_time";
    private final String ARR_TIME = "f07_arrival_time";
    private final String TOT_FLIGHT_TIME = "f09_total_flight_time";

    private Button mCreate;
    private Button mRead;
    private Button mUpdate;
    private Button mDelete;

    private Button mReset;

    private EditText mReadID;
    private EditText mUpdateID;
    private EditText mDeleteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create(COLLECTION_NAME);
            }
        });

        mRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                read(COLLECTION_NAME, mReadID.getText().toString());
            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(COLLECTION_NAME, mUpdateID.getText().toString() );
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(COLLECTION_NAME, mDeleteID.getText().toString());
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

    }

    /**
     * REQUIRES: String: collection name
     * MODIFIES:
     * EFFECTS: Creates a new document with automatic ID
     * */
    private void create(String collectionName) {

        Object obj = setDocumentObject();

        //  add a new object document
        db.collection(collectionName)
                .add(obj)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    /**
     * REQUIRES: String: collection name, String: document ID
     * MODIFIES:
     * EFFECTS: Reads the selected document, given the document ID
     * */
    private void read(String collectionName, final String documentID) {

        db.collection(collectionName).document(documentID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                         if (task.isSuccessful()) {
                             DocumentSnapshot document = task.getResult();
                             if (document.exists()) {
                                 // get document and format string
                                 String[] data = document.getData().toString()
                                                                   .replace("{", "")
                                                                   .replace("}", "")
                                                                   .split(", ");
                                 // sort string array
                                 Arrays.sort(data);
                                 // create string to display (log)
                                 String doc = "";
                                 for (String s : data) {
                                     doc += s + "\n";
                                 }
                                 Log.d(TAG, "DocumentSnapshot data:\n" + doc);
                             } else {
                                 Log.d(TAG, "No such document");
                             }
                         } else {
                             Log.d(TAG, "Get failed with: ", task.getException());
                         }
                    }
                });
                /*
                //  READ ALL DOCUMENTS:
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting document " + task.getException());
                        }
                    }
                });*/
    }

    /**
     * REQUIRES: String: collection name, String: document ID
     * MODIFIES: The selected document in the database
     * EFFECTS: Updates an existing document given the document ID
     * */
   private void update(String collectionName, String documentID) {

        if (!documentID.equals("")) {

            LinkedHashMap<String, String> objDoc = setDocumentObject();
            LinkedHashMap<String, Object> obj = new LinkedHashMap<>();

            for (String k : objDoc.keySet()) {
                if (!k.equals(INVOICE) && !k.equals(DATE) && !objDoc.get(k).equals(""))
                    obj.put(k, objDoc.get(k));
            }

            db.collection(collectionName).document(documentID)
                    .update(obj)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error updating document", e);
                        }
                    });

        } else {
            Log.w(TAG, "No document ID selected");
        }

    }

    /**
     * REQUIRES: String: collection name, String: document ID
     * MODIFIES: The selected document in the database
     * EFFECTS: Deletes an existing document given the document ID
     * */
    private void delete(String collectionName, String documentID) {

        if (!documentID.equals("")) {

            db.collection(collectionName).document(documentID)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot succesfully deleted!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error deleting document");
                        }
                    });

        } else {
            Log.w(TAG, "No document ID selected");
        }

    }

    /**
     * REQUIRES:
     * MODIFIES: this
     * EFFECTS: Initializes buttons and document ID EditText handlers
     * */
    private void init() {

        mCreate = findViewById(R.id.b_create);
        mRead = findViewById(R.id.b_read);
        mUpdate = findViewById(R.id.b_update);
        mDelete = findViewById(R.id.b_delete);

        mReset = findViewById(R.id.b_reset);

        mReadID = findViewById(R.id.edt_doc_id_read);
        mUpdateID = findViewById(R.id.edt_doc_id_update);
        mDeleteID = findViewById(R.id.edt_doc_id_delete);

    }

    /**
     * REQUIRES:
     * MODIFIES: this
     * EFFECTS: Resets all EditText handlers except document IDs
     * */
    private void reset() {

        EditText mMarca = findViewById(R.id.edt_marca);
        EditText mInvoice = findViewById(R.id.edt_invoice);
        EditText mDate = findViewById(R.id.edt_ddate);
        EditText mName = findViewById(R.id.edt_name);
        EditText mDep = findViewById(R.id.edt_dep);
        EditText mDepTime = findViewById(R.id.edt_dep_time);
        EditText mArr = findViewById(R.id.edt_arr);
        EditText mArrTime = findViewById(R.id.edt_arr_time);
        EditText mLandings = findViewById(R.id.edt_landings);
        EditText mComment = findViewById(R.id.edt_comment);

        mMarca.getText().clear();
        mInvoice.getText().clear();
        mDate.getText().clear();
        mName.getText().clear();
        mDep.getText().clear();
        mDepTime.getText().clear();
        mArr.getText().clear();
        mArrTime.getText().clear();
        mLandings.getText().clear();
        mComment.getText().clear();
    }

    /**
     * REQUIRES:
     * MODIFIES: this
     * EFFECTS: Returns a LinkedHashMap that contains all key and value pairs to create a new document
     * */
    private LinkedHashMap<String, String> setDocumentObject() {

        EditText mMarca = findViewById(R.id.edt_marca);
        EditText mInvoice = findViewById(R.id.edt_invoice);
        EditText mDate = findViewById(R.id.edt_ddate);
        EditText mName = findViewById(R.id.edt_name);
        EditText mDep = findViewById(R.id.edt_dep);
        EditText mDepTime = findViewById(R.id.edt_dep_time);
        EditText mArr = findViewById(R.id.edt_arr);
        EditText mArrTime = findViewById(R.id.edt_arr_time);
        EditText mLandings = findViewById(R.id.edt_landings);
        String mTotFlightTime = calculateTotalFlightTime(mDepTime.getText().toString(), mArrTime.getText().toString());
        EditText mComment = findViewById(R.id.edt_comment);

        LinkedHashMap<String, String> document = new LinkedHashMap<>();

        final String MARCA = "f00_marca";
        /*final String INVOICE = "f01_invoice";
        final String DATE = "f02_date";*/
        final String NAME = "f03_name";
        final String DEP = "f04_departure";
        //final String DEP_TIME = "f05_depart_time";
        final String ARR = "f06_arrival";
        //final String ARR_TIME = "f07_arrival_time";
        final String LANDINGS = "f08_landings";
        //final String TOT_FLIGHT_TIME = "f09_total_flight_time";
        final String COMMENT = "f10_custom";

        String marca = mMarca.getText().toString().equals("") ? "" : mMarca.getText().toString();
        String invoice = mInvoice.getText().toString().equals("") ? "" : mInvoice.getText().toString();
        String date = mDate.getText().toString().equals("") ? "" : mDate.getText().toString();
        String name = mName.getText().toString().equals("") ? "" : mName.getText().toString();
        String dep = mDep.getText().toString().equals("") ? "" : mDep.getText().toString();
        String dep_time = mDepTime.getText().toString().equals("") ? "" : mDepTime.getText().toString();
        String arr = mArr.getText().toString().equals("") ? "" : mArr.getText().toString();
        String arr_time = mArrTime.getText().toString().equals("") ? "" : mArrTime.getText().toString();
        String landings = mLandings.getText().toString().equals("") ? "" : mLandings.getText().toString();
        String comment = mComment.getText().toString().equals("") ? "" : mComment.getText().toString();

        document.put(MARCA, marca);
        document.put(INVOICE, invoice);
        document.put(DATE, date);
        document.put(NAME, name);
        document.put(DEP, dep);
        document.put(DEP_TIME, dep_time);
        document.put(ARR, arr);
        document.put(ARR_TIME, arr_time);
        document.put(LANDINGS, landings);
        document.put(TOT_FLIGHT_TIME, mTotFlightTime);
        document.put(COMMENT, comment);

        return document;
    }


    /**
     * REQUIRES: String: depart time, String: time of arrival
     * MODIFIES: this
     * EFFECTS: Returns the calculated total flight time or empty string if fails
     * */
    @SuppressLint("DefaultLocale")
    private String calculateTotalFlightTime(String depart, String arrival) {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        try {

            Date depTime = formatter.parse(depart);
            Date arrTime = formatter.parse(arrival);

            if (arrTime.compareTo(depTime) >= 0) {

                long totTime = (arrTime.getTime() - depTime.getTime()) / 1000;
                long hours = (totTime / 60 / 60) >= 0 && (totTime / 60 / 60) <= 23 ? (totTime / 60 / 60) : -1;
                long minutes = ((totTime / 60) %  60) >= 0 && ((totTime / 60) %  60) <= 59 ? ((totTime / 60) %  60) : -1;

                if (hours != -1 && minutes != -1)
                    return String.format("%02d:%02d", hours, minutes);

                System.out.println("Error in hour or minutes value:\n\t0 <= hour <= 23 and 0 <= minutes <= 59");

            } else {
                System.out.println(TAG + "Error: time of arrival must be equal or greater than depart time");
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return "";

    }

}
