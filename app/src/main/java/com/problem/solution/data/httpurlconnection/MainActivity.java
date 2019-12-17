package com.problem.solution.data.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

  private static final String GLOBANT_URL = "www.globant.com";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    try {
      onRetrieveWebPage();
      onPostContent();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void onRetrieveWebPage() throws IOException {
    URL url = new URL(GLOBANT_URL);
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    try {
      InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
      readStream(in);
    } finally {
      httpURLConnection.disconnect();
    }
  }

  private void onPostContent() throws IOException {
    URL url = new URL(GLOBANT_URL);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    try {
      urlConnection.setDoOutput(true);
      urlConnection.setChunkedStreamingMode(0);

      OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
      writeStream(out);

      InputStream in = new BufferedInputStream(urlConnection.getInputStream());
      readStream(in);
    } finally {
      urlConnection.disconnect();
    }
  }

  /**
   * method that reads the input stream
   * @param inputStream an input stream of bytes
   */
  private void readStream(InputStream inputStream) { }

  /**
   * method that writes to output stream
   * @param outputStream an output stream accepts output bytes
   * and sends them to some sink.
   */
  private void writeStream(OutputStream outputStream) {}
}
