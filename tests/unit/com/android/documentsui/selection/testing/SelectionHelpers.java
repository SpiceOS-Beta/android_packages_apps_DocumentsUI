/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.documentsui.selection.testing;

import com.android.documentsui.selection.DefaultSelectionHelper;
import com.android.documentsui.selection.DefaultSelectionHelper.SelectionMode;
import com.android.documentsui.selection.SelectionHelper;
import com.android.documentsui.selection.SelectionHelper.SelectionPredicate;

import java.util.Collections;
import java.util.List;

public class SelectionHelpers {

    public static final SelectionPredicate CAN_SET_ANYTHING = new SelectionPredicate() {
        @Override
        public boolean canSetStateForId(String id, boolean nextState) {
            return true;
        }

        @Override
        public boolean canSetStateAtPosition(int position, boolean nextState) {
            return true;
        }
    };

    private SelectionHelpers() {}

    public static SelectionHelper createTestInstance() {
        return createTestInstance(Collections.emptyList());
    }

    public static SelectionHelper createTestInstance(List<String> docs) {
        return createTestInstance(docs, DefaultSelectionHelper.MODE_MULTIPLE);
    }

    public static SelectionHelper createTestInstance(
            List<String> items, @SelectionMode int mode) {
        return createTestInstance(new TestAdapter(items), mode, CAN_SET_ANYTHING);
    }

    public static SelectionHelper createTestInstance(
            TestAdapter adapter, @SelectionMode int mode, SelectionPredicate selPredicate) {
        return new DefaultSelectionHelper(
                mode, adapter, new TestStableIdProvider(adapter), selPredicate);
    }
}
