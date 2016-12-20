/*
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pub.devrel.easypermissions.sample;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import pub.devrel.easypermissions.BasePermissionActivity;
import pub.devrel.easypermissions.PermissionCallBackM;

public class MainActivity extends BasePermissionActivity {

    private static final String TAG = "MainActivity";

    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button click listener that will request one permission.
        findViewById(R.id.button_camera).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cameraTask();
            }
        });

        // Button click listener that will request two permissions.
        findViewById(R.id.button_location_and_wifi).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                locationAndContactsTask();
            }
        });
    }

    public void cameraTask() {
        requestPermission(RC_CAMERA_PERM, new String[] { Manifest.permission.CAMERA },
                          getString(R.string.rationale_camera), new PermissionCallBackM() {
                    @Override public void onPermissionGrantedM(int requestCode, String... perms) {
                        Toast.makeText(MainActivity.this, "TODO: Camera Granted", Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override public void onPermissionDeniedM(int requestCode, String... perms) {
                        onPermissionDenied(requestCode, perms);
                    }
                });

    }

    public void locationAndContactsTask() {
        requestPermission(RC_LOCATION_CONTACTS_PERM, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS
        }, getString(R.string.rationale_location_contacts), new PermissionCallBackM() {
            @Override public void onPermissionGrantedM(int requestCode, String... perms) {
                Toast.makeText(MainActivity.this, "TODO: LOCATION Granted", Toast.LENGTH_LONG)
                        .show();

            }

            @Override public void onPermissionDeniedM(int requestCode, String... perms) {
                onPermissionDenied(requestCode, perms);

            }
        });
    }

    public void onPermissionDenied(int requestCode, String... perms) {
        Toast.makeText(MainActivity.this, "onPermissionDenied:" + requestCode + ":" + perms.length, Toast.LENGTH_SHORT)
                .show();
    }
}
