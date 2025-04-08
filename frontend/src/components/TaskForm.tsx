import React, { useState } from "react";
import { COLORS } from "../const/colors";

interface TaskFormProps {
  onAdd: (title: string, description: string) => void;
}

const TaskForm: React.FC<TaskFormProps> = ({ onAdd }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = () => {
    if (!title.trim()) return;
    onAdd(title, description);
    setTitle("");
    setDescription("");
  };

  return (
    <div>
      <input
        data-testid="task-title-input"
        type="text"
        className={`w-full border ${COLORS.inputBorder} p-2 mb-3 rounded ${COLORS.inputBg} ${COLORS.inputText}`}
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        data-testid="task-desc-input"
        className={`w-full border ${COLORS.inputBorder} p-2 mb-3 rounded ${COLORS.inputBg} ${COLORS.inputText}`}
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <button
        data-testid="add-task-btn"
        className={`${COLORS.buttonBg} ${COLORS.buttonText} px-4 py-2 rounded ${COLORS.buttonHover} transition`}
        onClick={handleSubmit}
      >
        Add
      </button>
    </div>
  );
};

export default TaskForm;
