package com.kubejs.wiki;

import com.kubejs.wiki.json.JsonArray;
import com.kubejs.wiki.json.JsonObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class DocClass extends TypedDocumentedObject {
	public Path file;
	public DocNamespace namespace;
	public long id = 0L;
	public List<String> lines;
	public String path = "";
	public String name = "";
	public String classtype = "class";
	public String typescript = "";
	public DocType extendsClass;
	public List<String> generics = new ArrayList<>(0);
	public List<DocType> implementsClass = new ArrayList<>(0);
	public List<DocField> fields = new ArrayList<>(0);
	public List<DocMethod> methods = new ArrayList<>(5);
	public List<String> events = new ArrayList<>(0);
	public Boolean canCancel = null;
	public List<DocExample> examples = new ArrayList<>(0);
	public String binding = "";

	public String getPathName() {
		int i = path.lastIndexOf('/');
		return i == -1 ? path : path.substring(i + 1);
	}

	@Override
	public JsonObject toJson() {
		JsonObject o = super.toJson();

		o.add("id", id);
		o.add("path", path);

		if (!name.isEmpty()) {
			o.add("name", name);
		}

		if (!classtype.equals("class")) {
			o.add("classtype", classtype);
		}

		if (!typescript.isEmpty()) {
			o.add("typescript", typescript);
		}

		if (!events.isEmpty()) {
			JsonArray a = new JsonArray();

			for (String s : events) {
				a.add(s);
			}

			o.add("events", a);
		}

		if (canCancel != null) {
			o.add("canCancel", canCancel);
		}

		if (!examples.isEmpty()) {
			JsonArray a = new JsonArray();

			for (DocExample e : examples) {
				a.add(e.toJson());
			}

			o.add("examples", a);
		}

		if (!binding.isEmpty()) {
			o.add("binding", binding);
		}

		if (extendsClass != null) {
			o.add("extends", extendsClass.toJson());
		}

		if (!generics.isEmpty()) {
			JsonArray a = new JsonArray();

			for (String s : generics) {
				a.add(s);
			}

			o.add("generics", a);
		}

		if (!implementsClass.isEmpty()) {
			JsonArray a = new JsonArray();

			for (DocType c : implementsClass) {
				a.add(c.toJson());
			}

			o.add("implements", a);
		}

		if (!fields.isEmpty()) {
			JsonArray a = new JsonArray();

			for (DocField c : fields) {
				a.add(c.toJson());
			}

			o.add("fields", a);
		}

		if (!methods.isEmpty()) {
			JsonArray a = new JsonArray();

			for (DocMethod c : methods) {
				a.add(c.toJson());
			}

			o.add("methods", a);
		}

		return o;
	}
}