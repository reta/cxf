/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.service.model.MessagePartInfo;

public class MessageContentsList extends ArrayList<Object> {
    public static final Object REMOVED_MARKER = new Object();
    private static final long serialVersionUID = -5780720048950696258L;
    private final Set<Integer> removed = new HashSet<>();

    public MessageContentsList() {
        super(6);
    }
    public MessageContentsList(Object ... values) {
        super(Arrays.asList(values));
    }
    public MessageContentsList(List<?> values) {
        super(values);
    }

    public static MessageContentsList getContentsList(Message msg) {
        List<Object> o = CastUtils.cast(msg.getContent(List.class));
        if (o == null) {
            return null;
        }
        if (!(o instanceof MessageContentsList)) {
            MessageContentsList l2 = new MessageContentsList(o);
            msg.setContent(List.class, l2);
            return l2;
        }
        return (MessageContentsList)o;
    }

    public Object set(int idx, Object value) {
        ensureSize(idx);

        if (value != REMOVED_MARKER) {
            removed.remove(idx);
        }

        return super.set(idx, value);
    }

    private void ensureSize(int idx) {
        while (idx >= size()) {
            removed.add(size());
            add(null);
        }
    }

    public Object put(MessagePartInfo key, Object value) {
        return set(key.getIndex(), value);
    }

    public boolean hasValue(MessagePartInfo key) {
        if (key.getIndex() >= size()) {
            return false;
        }
        return !removed.contains(key.getIndex());
    }

    public Object get(MessagePartInfo key) {
        return super.get(key.getIndex());
    }

    public void remove(MessagePartInfo key) {
        put(key, null);
    }
}
