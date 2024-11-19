package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AddPostActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription, editTextLocation;
    private Button buttonSavePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextLocation = findViewById(R.id.edit_text_location);
        buttonSavePost = findViewById(R.id.button_save_post);

        buttonSavePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                String location = editTextLocation.getText().toString();

                if (title.isEmpty() || description.isEmpty() || location.isEmpty()) {
                    Toast.makeText(AddPostActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("title", title);
                resultIntent.putExtra("description", description);
                resultIntent.putExtra("location", location);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
   });
}
}