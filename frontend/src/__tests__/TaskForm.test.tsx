import { render, screen, fireEvent } from '@testing-library/react';
import TaskForm from '../components/TaskForm';
import { describe, it, expect, vi, beforeEach } from 'vitest';

describe('TaskForm', () => {
  let onAdd: ReturnType<typeof vi.fn>;

  beforeEach(() => {
    onAdd = vi.fn(); // fresh mock for each test
  });

  it('renders input fields and button', () => {
    render(<TaskForm onAdd={onAdd} />);
    
    expect(screen.getByPlaceholderText(/Title/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText(/Description/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /Add/i })).toBeInTheDocument(); // FIXED
  });

  it('submits data correctly and clears fields', () => {
    render(<TaskForm onAdd={onAdd} />);

    const titleInput = screen.getByPlaceholderText(/Title/i);
    const descInput = screen.getByPlaceholderText(/Description/i);
    const addButton = screen.getByRole('button', { name: /Add/i }); // FIXED

    fireEvent.change(titleInput, { target: { value: 'New Task' } });
    fireEvent.change(descInput, { target: { value: 'Task description' } });
    fireEvent.click(addButton);

    expect(onAdd).toHaveBeenCalledWith('New Task', 'Task description');
    expect(titleInput).toHaveValue('');
    expect(descInput).toHaveValue('');
  });

  it('does not call onAdd when title is empty', () => {
    render(<TaskForm onAdd={onAdd} />);
    
    const addButton = screen.getByRole('button', { name: /Add/i }); // FIXED
    fireEvent.click(addButton);

    expect(onAdd).not.toHaveBeenCalled();
  });
});
