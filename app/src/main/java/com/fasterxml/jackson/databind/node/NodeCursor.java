package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;
import java.util.Map;

abstract class NodeCursor extends JsonStreamContext {
    protected String _currentName;
    protected Object _currentValue;
    protected final NodeCursor _parent;

    protected static final class ArrayCursor extends NodeCursor {
        protected Iterator<JsonNode> _contents;
        protected JsonNode _currentNode;

        public ArrayCursor(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(1, nodeCursor);
            this._contents = jsonNode.elements();
        }

        @Override
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }

        @Override
        public JsonNode currentNode() {
            return this._currentNode;
        }

        @Override
        public JsonToken endToken() {
            return JsonToken.END_ARRAY;
        }

        @Override
        public JsonStreamContext getParent() {
            return super.getParent();
        }

        @Override
        public JsonToken nextToken() {
            if (!this._contents.hasNext()) {
                this._currentNode = null;
                return null;
            }
            JsonNode next = this._contents.next();
            this._currentNode = next;
            return next.asToken();
        }

        @Override
        public JsonToken nextValue() {
            return nextToken();
        }
    }

    protected static final class ObjectCursor extends NodeCursor {
        protected Iterator<Map.Entry<String, JsonNode>> _contents;
        protected Map.Entry<String, JsonNode> _current;
        protected boolean _needEntry;

        public ObjectCursor(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(2, nodeCursor);
            this._contents = ((ObjectNode) jsonNode).fields();
            this._needEntry = true;
        }

        @Override
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }

        @Override
        public JsonNode currentNode() {
            Map.Entry<String, JsonNode> entry = this._current;
            if (entry == null) {
                return null;
            }
            return entry.getValue();
        }

        @Override
        public JsonToken endToken() {
            return JsonToken.END_OBJECT;
        }

        @Override
        public JsonStreamContext getParent() {
            return super.getParent();
        }

        @Override
        public JsonToken nextToken() {
            if (!this._needEntry) {
                this._needEntry = true;
                return this._current.getValue().asToken();
            }
            if (!this._contents.hasNext()) {
                this._currentName = null;
                this._current = null;
                return null;
            }
            this._needEntry = false;
            Map.Entry<String, JsonNode> next = this._contents.next();
            this._current = next;
            this._currentName = next != null ? next.getKey() : null;
            return JsonToken.FIELD_NAME;
        }

        @Override
        public JsonToken nextValue() {
            JsonToken jsonTokenNextToken = nextToken();
            return jsonTokenNextToken == JsonToken.FIELD_NAME ? nextToken() : jsonTokenNextToken;
        }
    }

    protected static final class RootCursor extends NodeCursor {
        protected boolean _done;
        protected JsonNode _node;

        public RootCursor(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(0, nodeCursor);
            this._done = false;
            this._node = jsonNode;
        }

        @Override
        public boolean currentHasChildren() {
            return false;
        }

        @Override
        public JsonNode currentNode() {
            return this._node;
        }

        @Override
        public JsonToken endToken() {
            return null;
        }

        @Override
        public JsonStreamContext getParent() {
            return super.getParent();
        }

        @Override
        public JsonToken nextToken() {
            if (this._done) {
                this._node = null;
                return null;
            }
            this._done = true;
            return this._node.asToken();
        }

        @Override
        public JsonToken nextValue() {
            return nextToken();
        }

        @Override
        public void overrideCurrentName(String str) {
        }
    }

    public NodeCursor(int i, NodeCursor nodeCursor) {
        this._type = i;
        this._index = -1;
        this._parent = nodeCursor;
    }

    public abstract boolean currentHasChildren();

    public abstract JsonNode currentNode();

    public abstract JsonToken endToken();

    @Override
    public final String getCurrentName() {
        return this._currentName;
    }

    @Override
    public Object getCurrentValue() {
        return this._currentValue;
    }

    @Override
    public final NodeCursor getParent() {
        return this._parent;
    }

    public final NodeCursor iterateChildren() {
        JsonNode jsonNodeCurrentNode = currentNode();
        if (jsonNodeCurrentNode == null) {
            throw new IllegalStateException("No current node");
        }
        if (jsonNodeCurrentNode.isArray()) {
            return new ArrayCursor(jsonNodeCurrentNode, this);
        }
        if (jsonNodeCurrentNode.isObject()) {
            return new ObjectCursor(jsonNodeCurrentNode, this);
        }
        throw new IllegalStateException("Current node of type " + jsonNodeCurrentNode.getClass().getName());
    }

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();

    public void overrideCurrentName(String str) {
        this._currentName = str;
    }

    @Override
    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }
}
