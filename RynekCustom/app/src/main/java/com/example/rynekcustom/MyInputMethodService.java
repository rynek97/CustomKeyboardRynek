package com.example.rynekcustom;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

import java.io.InputStream;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

  public void toastMsg(String msg) {

      Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
      toast.show();

  }
    String isnfc = "";


    @Override
    public View onCreateInputView() {
        // get the KeyboardView and add our Keyboard layout to it
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.layout.number_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    public View onCreateInputView2() {
        // get the KeyboardView and add our Keyboard layout to it
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.layout.number_pad2);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }
    public View onCreateInputView3() {
        // get the KeyboardView and add our Keyboard layout to it
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.layout.number_pad3);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }



    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        switch (primaryCode) {
            case -7:

                CharSequence selectedText = ic.getSelectedText(0);
                ic.commitText("THIS IS A CUSTOM KEYBOARD !", 1);
                break;
            case 49:

                MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.sample);
                mediaPlayer.start();
                break;
            case 50:

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                break;
            case 51:

                toastMsg("Welcome RynekCustom app!");
                break;
            case 52:

                setInputView(onCreateInputView2());
                break;
            case 64:

                setInputView(onCreateInputView());
                break;
            case 53:

                NfcManager manager = (NfcManager) getApplicationContext().getSystemService(Context.NFC_SERVICE);
                NfcAdapter adapter = manager.getDefaultAdapter();

                if (adapter != null && adapter.isEnabled()){
                    isnfc = "NFC/ON";
                }   else {
                    isnfc = "NFC/OFF";
                }
                toastMsg(isnfc);
                break;
            case 54:

                Intent intent2 = new Intent(Settings.ACTION_NFC_SETTINGS);
                startActivity(intent2);
                break;
            case 55:

                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                    toastMsg("BT don't supported!");
                } else if (!mBluetoothAdapter.isEnabled()) {
                    toastMsg("BT disabled!");
                } else {
                    toastMsg("BT enabled!");
                }
                break;
            case 56:

                Intent intent3 = new Intent();
                intent3.setAction(android.content.Intent.ACTION_VIEW);
                intent3.setType("image/*");
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
                break;
            case 57:

                setInputView(onCreateInputView3());
                break;
            case -4:

                setInputView(onCreateInputView());
                break;
            default:

        }

    }

    @Override
    public void onPress(int primaryCode) { }

    @Override
    public void onRelease(int primaryCode) { }

    @Override
    public void onText(CharSequence text) { }

    @Override
    public void swipeLeft() { }

    @Override
    public void swipeRight() { }

    @Override
    public void swipeDown() { }

    @Override
    public void swipeUp() { }
}