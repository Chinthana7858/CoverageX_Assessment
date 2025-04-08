import React from "react";
import { Task } from "../types/Task";
import { COLORS } from "../const/colors";
interface TaskCardProps extends Task {
  onDone: (id: number) => void;
}

const TaskCard: React.FC<TaskCardProps> = ({
  id,
  title,
  description,
  onDone,
}) => {
  return (
    <div className={`bg-${COLORS.foreground} p-4 mb-4 rounded flex justify-between items-center`}>
      <div>
        <h3 className={`font-bold ${COLORS.titleText}`}>{title}</h3>
        <p className={` ${COLORS.lightText}`}>{description}</p>
      </div>
      <button
        className={`px-4 py-1 rounded border-2 border-gray-400 
          ${COLORS.buttonBg} ${COLORS.buttonHover} ${COLORS.buttonText}`}
        onClick={() => onDone(id)}
      >
        Done
      </button>
    </div>
  );
};

export default TaskCard;
