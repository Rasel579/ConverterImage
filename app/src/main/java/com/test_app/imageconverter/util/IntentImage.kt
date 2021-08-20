package com.test_app.imageconverter.util

import android.content.Intent
import android.provider.MediaStore

object IntentImage : Intent(ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)